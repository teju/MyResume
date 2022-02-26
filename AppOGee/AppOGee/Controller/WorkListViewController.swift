//
//  WorkListViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 31/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit

class WorkListViewController: UIViewController {
   
    @IBOutlet weak var imageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = false
    }

    func zoomOut(imageview : UIView) {
        UIView.animate(withDuration: 0.5, delay: 0.0, options: UIView.AnimationOptions.curveEaseOut, animations: {
                // HERE
            imageview.transform = CGAffineTransform.identity.scaledBy(x: 20, y: 20) // Scale your image

            }) { (finished) in
                 
        }
    }
  
 }

