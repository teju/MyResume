//
//  TabViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 31/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit
import AMTabView

class TabViewController: AMTabsViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setTabsControllers()
        // Do any additional setup after loading the view.
    }
    

   private func setTabsControllers() {
      let storyboard = UIStoryboard(name: "Main", bundle: nil)
      let graveViewController = storyboard.instantiateViewController(withIdentifier: "WorkTabViewController")
      let bumpkinViewController = storyboard.instantiateViewController(withIdentifier: "InitialViewController")
      let fireworkViewController = storyboard.instantiateViewController(withIdentifier: "InitialViewController")
      let ghostViewController = storyboard.instantiateViewController(withIdentifier: "InitialViewController")

      viewControllers = [
        graveViewController,
        bumpkinViewController,
        fireworkViewController,
        ghostViewController
      ]
    }

}
