//
//  LoginViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 03/01/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit
import GoogleSignIn

class LoginViewController: UIViewController {

    @IBOutlet weak var imageview: UIImageView!
    @IBOutlet weak var bg_img: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        GIDSignIn.sharedInstance()?.delegate = self
        GIDSignIn.sharedInstance()?.presentingViewController = self

    }
    @IBAction func googleSignIn(_ sender: Any) {
        GIDSignIn.sharedInstance()?.signIn()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = true
       // animate(self.imageview)
        //animateBackground()
    }
    @IBAction func skip(_ sender: Any) {
        if(ConnectionManager.shared.hasConnectivity()) {
            let storyboard = UIStoryboard(name: "Main", bundle: nil)
            let initialViewController = storyboard.instantiateViewController(withIdentifier: "TabViewController")
            self.navigationController?.pushViewController(initialViewController, animated: true)
        } else {
            let alert = UIAlertController(title: "No Internet Connection", message: "", preferredStyle: UIAlertController.Style.alert)
            alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    func animateBackground() {
        let oldCenter = imageview.center
            let newCenter = CGPoint(x: oldCenter.x , y: oldCenter.y - 300)
            UIView.animate(withDuration: 2.0, delay: 0.0, options:
            [.curveLinear, .repeat], animations: {
                self.imageview.center = newCenter
            }, completion: nil)
       
    }
    
    func animate(_ image: UIImageView) {
        UIView.animate(withDuration: 12.0, delay: 0.0, options: [.repeat, .curveLinear], animations: {
                self.bg_img.frame = self.bg_img.frame.offsetBy(dx: -1 * self.bg_img.frame.size.width, dy: 0.0)
                self.imageview.frame = self.imageview.frame.offsetBy(dx: -1 * self.imageview.frame.size.width, dy: 0.0)
            }, completion: nil)
    }
}

extension LoginViewController: GIDSignInDelegate {
    
    func sign(_ signIn: GIDSignIn!,
              didSignInFor user: GIDGoogleUser!,
              withError error: Error!) {
        
        // Check for sign in error
        if let error = error {
            if (error as NSError).code == GIDSignInErrorCode.hasNoAuthInKeychain.rawValue {
                print("The user has not signed in before or they have since signed out.")
            } else {
                print("\(error.localizedDescription)")
            }
            return
        }
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let initialViewController = storyboard.instantiateViewController(withIdentifier: "TabViewController")
        self.navigationController?.pushViewController(initialViewController, animated: true)
        // Post notification after user successfully sign in
        NotificationCenter.default.post(name: .signInGoogleCompleted, object: nil)
    }
}

// MARK:- Notification names
extension Notification.Name {
    
    /// Notification when user successfully sign in using Google
    static var signInGoogleCompleted: Notification.Name {
        return .init(rawValue: #function)
    }
}
