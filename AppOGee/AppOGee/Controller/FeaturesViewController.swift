//
//  FeaturesViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 25/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase

class FeaturesViewController: UIViewController ,UITableViewDataSource, UITableViewDelegate{
    var itemText: String?

    @IBOutlet weak var featurestableview: UITableView!
    
    var refPlayer = DatabaseReference()
    var playerList = NSMutableArray()
  
    override func viewDidLoad() {
        super.viewDidLoad()
    
        self.loadViewIfNeeded()
        featurestableview.delegate = self
        featurestableview.dataSource = self
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
           refPlayer = Database.database().reference().child("features/scrollImages");
            self.playerList.removeAllObjects()

           refPlayer.observe(DataEventType.value, with: {(snapshot) in
               let snap = snapshot.value as! NSMutableArray
               print(snap)
               
           })
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "featurescell",
                                                     for: indexPath as IndexPath) as! FeaturesTableViewCell
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 200
    }
}


