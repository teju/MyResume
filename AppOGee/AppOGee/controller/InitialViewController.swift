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
    
 
    override func viewDidLoad() {
        super.viewDidLoad()
//        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) { // Change `2.0` to the desired number of seconds.
//            let storyboard = UIStoryboard(name: "Main", bundle: nil)
//            let initialViewController = storyboard.instantiateViewController(withIdentifier: "TabViewController")
//            self.navigationController?.pushViewController(initialViewController, animated: true)
//        }
        
    }
    
    func dribbleAnim() {
        let spring = RBBSpringAnimation(keyPath: "position.y")

        spring.fromValue = NSNumber(value: -100.0)
        spring.toValue = NSNumber(value: 100.0)
        spring.velocity = 0
        spring.mass = 1
        spring.damping = 10
        spring.stiffness = 100

        spring.isAdditive = true
        spring.duration = spring.duration(forEpsilon: 0.01)
    }
}
