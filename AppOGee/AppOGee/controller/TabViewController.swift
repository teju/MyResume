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
    override func viewDidAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = true
    }

   private func setTabsControllers() {
      let storyboard = UIStoryboard(name: "Main", bundle: nil)
     
      let homeViewController = storyboard.instantiateViewController(withIdentifier: "MenuViewController")
      let featuresViewController = storyboard.instantiateViewController(withIdentifier: "MenuViewController")
       let worksViewController = storyboard.instantiateViewController(withIdentifier: "WorkTabViewController")
      let aboutViewController = storyboard.instantiateViewController(withIdentifier: "MenuViewController")

      viewControllers = [
        homeViewController,
        featuresViewController,
        worksViewController,
        aboutViewController
      ]
    }

}
