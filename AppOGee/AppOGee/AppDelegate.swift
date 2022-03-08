//
//  AppDelegate.swift
//  AppOGee
//
//  Created by Tejaswini N on 29/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase
import GoogleSignIn

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window:UIWindow?
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        FirebaseApp.configure()
        GIDSignIn.sharedInstance()?.clientID = "682386723547-kpfjfm7pletg2rkq4p64j595tbehvaul.apps.googleusercontent.com"

        return true
    }
    func application(_ app: UIApplication,
                     open url: URL,
                     options: [UIApplication.OpenURLOptionsKey : Any]) -> Bool {

        return ((GIDSignIn.sharedInstance()?.handle(url)) != nil)
    }
}

