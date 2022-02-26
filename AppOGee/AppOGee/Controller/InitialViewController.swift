//
//  LaunchViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 29/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit
import RBBAnimation

class InitialViewController: UIViewController {
    
 
    @IBOutlet weak var app_o_gee_logo: UIImageView!
    @IBOutlet weak var app_logo: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
       
        dribbleAnim()
    }
    
    func dribbleAnim() {
        UIView.animate(withDuration: 4.0, animations: {() -> Void in
            self.app_logo.transform = CGAffineTransform(scaleX: 1.5, y: 1.5)
        }, completion: {(_ finished: Bool) -> Void in
            self.app_o_gee_logo.isHidden = false
            DispatchQueue.main.asyncAfter(deadline: .now() + 1.0) {
                let storyboard = UIStoryboard(name: "Main", bundle: nil)
                let initialViewController = storyboard.instantiateViewController(withIdentifier: "LoginViewController")
                self.navigationController?.pushViewController(initialViewController, animated: true)
            }
           
        })
       
    }
}
extension UIView {

    @IBInspectable var cornerRadiusV: CGFloat {
        get {
            return layer.cornerRadius
        }
        set {
            layer.cornerRadius = newValue
            layer.masksToBounds = newValue > 0
        }
    }

    @IBInspectable var borderWidthV: CGFloat {
        get {
            return layer.borderWidth
        }
        set {
            layer.borderWidth = newValue
        }
    }

    @IBInspectable var borderColorV: UIColor? {
        get {
            return UIColor(cgColor: layer.borderColor!)
        }
        set {
            layer.borderColor = newValue?.cgColor
        }
    }
    @IBInspectable var shadowColor: UIColor?{
        set {
            guard let uiColor = newValue else { return }
            layer.shadowColor = uiColor.cgColor
        }
        get{
            guard let color = layer.shadowColor else { return nil }
            return UIColor(cgColor: color)
        }
    }

    @IBInspectable var shadowOpacity: Float{
        set {
            layer.shadowOpacity = newValue
        }
        get{
            return layer.shadowOpacity
        }
    }

    @IBInspectable var shadowOffset: CGSize{
        set {
            layer.shadowOffset = newValue
        }
        get{
            return layer.shadowOffset
        }
    }

    @IBInspectable var shadowRadius: CGFloat{
        set {
            layer.shadowRadius = newValue
        }
        get{
            return layer.shadowRadius
        }
    }
}
