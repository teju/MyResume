//
//  MenuViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 06/05/21.
//  Copyright © 2021 Tejaswini N. All rights reserved.
//

import UIKit

class HomeViewController: UITableViewController ,TabItem{
   
    var tabImage: UIImage? {
      return UIImage(named: "Home")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.delegate = self
        tableView.dataSource = self
        self.view.backgroundColor = UIColor.white
        tableView.contentInset = UIEdgeInsets(top: -20, left: 0, bottom: 0, right: 0);

    }
    
   
    
}
