//
//  LoginViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 03/01/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    @IBOutlet weak var imageview: UIImageView!
    @IBOutlet weak var bg_img: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = true
       // animate(self.imageview)
        //animateBackground()
    }
    @IBAction func skip(_ sender: Any) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let initialViewController = storyboard.instantiateViewController(withIdentifier: "TabViewController")
        self.navigationController?.pushViewController(initialViewController, animated: true)
    }
    
    func animateBackground() {
        let animationOptions = UIView.AnimationOptions.repeat
        let backgroundImage = UIImage(named:"sign_in_page.jpeg")!
        var amountToKeepImageSquare = backgroundImage.size.height - self.view.frame.size.height

        // UIImageView 1
        var backgroundImageView1 = UIImageView(image: backgroundImage)
        backgroundImageView1.frame = CGRect(x: self.view.frame.origin.x, y: self.view.frame.origin.y, width: backgroundImage.size.width - amountToKeepImageSquare, height: self.view.frame.size.height)
        
        self.view.addSubview(backgroundImageView1)

        // UIImageView 2
        var backgroundImageView2 = UIImageView(image: backgroundImage)
        backgroundImageView2.frame = CGRect(x: backgroundImageView1.frame.size.width, y: self.view.frame.origin.y, width: backgroundImage.size.width - amountToKeepImageSquare, height: self.view.frame.height)
        self.view.addSubview(backgroundImageView2)

        // Animate background
        UIView.animate(withDuration: 6.0, delay: 0.0, options: animationOptions, animations: {
            backgroundImageView1.frame = backgroundImageView1.frame.offsetBy(dx: -1 * backgroundImageView1.frame.size.width, dy: 0.0)
            backgroundImageView2.frame = backgroundImageView2.frame.offsetBy(dx: -1 * backgroundImageView2.frame.size.width, dy: 0.0)
            }, completion: nil)

       
    }
    
    func animate(_ image: UIImageView) {
        UIView.animate(withDuration: 12.0, delay: 0.0, options: [.repeat, .curveLinear], animations: {
                self.bg_img.frame = self.bg_img.frame.offsetBy(dx: -1 * self.bg_img.frame.size.width, dy: 0.0)
                self.imageview.frame = self.imageview.frame.offsetBy(dx: -1 * self.imageview.frame.size.width, dy: 0.0)
            }, completion: nil)
    }
}
