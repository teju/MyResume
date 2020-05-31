//
//  LaunchViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 29/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit
import AMTabView

class InitialViewController: UIViewController ,TabItem{
    
    @IBOutlet weak var image_1: UIImageView!
    @IBOutlet weak var btnImg1: UIButton!

    var tabImage: UIImage? {
      return UIImage(named: "dummy")
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        rotate2(imageView: image_1, aCircleTime: 5.0)
        
    }
    
    func rotate2(imageView: UIImageView, aCircleTime: Double) { //UIView
     
         UIView.animate(withDuration: aCircleTime/5, delay: 0.0, options: .curveLinear, animations: {
             imageView.transform = CGAffineTransform(rotationAngle: CGFloat(Double.pi))
         }, completion: { finished in
             UIView.animate(withDuration: aCircleTime/5, delay: 0.0, options: .curveLinear, animations: {
                 imageView.transform = CGAffineTransform(rotationAngle: CGFloat(Double.pi*2))
             }, completion: { finished in
                 self.rotate2(imageView: imageView, aCircleTime: aCircleTime)
             })
         })
    }
    
    func zoomOut(imageview : UIImageView) {
        UIView.animate(withDuration: 1.0, delay: 0.0, options: UIView.AnimationOptions.curveEaseOut, animations: {
                // HERE
            imageview.transform = CGAffineTransform.identity.scaledBy(x: 3, y: 3) // Scale your image

            }) { (finished) in
                 //imageview.transform = CGAffineTransform.identity // undo in 1 seconds

                 let storyboard = UIStoryboard(name: "Main", bundle: nil)
                 let initialViewController = storyboard.instantiateViewController(withIdentifier: "TabViewController")
                 self.navigationController?.pushViewController(initialViewController, animated: true)
        }
    }
    
    func nukeAllAnimations(imageview : UIImageView) {
         imageview.layer.removeAllAnimations()
         imageview.layoutIfNeeded()
    }
    
    @IBAction func image1_click(_ sender: Any) {
        nukeAllAnimations(imageview: image_1)
        zoomOut(imageview: image_1)
        

    }
}
