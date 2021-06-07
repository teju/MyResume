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
    
 
    @IBOutlet weak var app_logo: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
//        DispatchQueue.main.asyncAfter(deadline: .now() + 2.0) { // Change `2.0` to the desired number of seconds.
//            let storyboard = UIStoryboard(name: "Main", bundle: nil)
//            let initialViewController = storyboard.instantiateViewController(withIdentifier: "TabViewController")
//            self.navigationController?.pushViewController(initialViewController, animated: true)
//        }
        dribbleAnim()
    }
    
    func dribbleAnim() {
        let animator = UIDynamicAnimator(referenceView: view)

        let gravityBehavior = UIGravityBehavior(items: [app_logo])
        animator.addBehavior(gravityBehavior)

        let collisionBehavior = UICollisionBehavior(items: [app_logo])
        collisionBehavior.translatesReferenceBoundsIntoBoundary = true
        animator.addBehavior(collisionBehavior)

        let elasticityBehavior = UIDynamicItemBehavior(items: [app_logo])
        elasticityBehavior.elasticity = 0.7
        animator.addBehavior(elasticityBehavior)
    }
}
