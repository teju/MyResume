//
//  AppDelegate.swift
//  AppOGee
//
//  Created by Tejaswini N on 29/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window:UIWindow?
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        self.window = UIWindow(frame: UIScreen.main.bounds)

        let storyboard = UIStoryboard.init(name: "Main", bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: "InitialViewController") as! InitialViewController
        let navigationController = UINavigationController.init(rootViewController: viewController)
        //navigationController.setNavigationBarHidden(true, animated: true)

        self.window?.rootViewController = navigationController

        self.window?.makeKeyAndVisible()
        return true
    }

}

