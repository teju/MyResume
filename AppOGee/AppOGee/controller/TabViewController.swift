//
//  TabViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 31/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit

class TabViewController: AMTabsViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setTabsControllers()
        // Do any additional setup after loading the view.
    }
    

   private func setTabsControllers() {
      let storyboard = UIStoryboard(name: "Main", bundle: nil)
      let graveViewController = storyboard.instantiateViewController(withIdentifier: "WorkTabViewController")
      let bumpkinViewController = storyboard.instantiateViewController(withIdentifier: "MenuViewController")
    let menuViewController = storyboard.instantiateViewController(withIdentifier: "MenuViewController")

      let fireworkViewController = storyboard.instantiateViewController(withIdentifier: "MenuViewController")
      let ghostViewController = storyboard.instantiateViewController(withIdentifier: "MenuViewController")

      viewControllers = [
        graveViewController,
        bumpkinViewController,
        menuViewController,
        fireworkViewController,
        ghostViewController
      ]
    }

}
