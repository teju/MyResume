//
//  FeaturesViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 25/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class FeaturesViewController: UIViewController ,UITableViewDataSource, UITableViewDelegate{
    var itemText: String?

    @IBOutlet weak var featurestableview: UITableView!
    
  
    override func viewDidLoad() {
        super.viewDidLoad()
    
        self.loadViewIfNeeded()
        featurestableview.delegate = self
        featurestableview.dataSource = self
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        FirebaseApp.configure()
           refPlayer = Database.database().reference().child("Player");
           
           refPlayer.observe(DataEventType.value, with: {(snapshot) in
               if snapshot.childrenCount > 0 {
                   self.playerList.removeAll()
                   
                   for players in snapshot.children.allObjects as! [DataSnapshot]{
                       let playerOject = players.value as? [String: AnyObject]
                     //  let playerId = playerOject?["id"]
                       let playerName = playerOject?["navn"]
                       let playerweek = playerOject?["uke"]
                     // let playerPlayed = playerOject?["spillet"]
                      // let playerExtra = playerOject?["extra"]
                    //   let playerWalkover = playerOject?["walkover"]
                    //   let playerKommentar = playerOject?["kommentar"]
                       
                       
                       
                       let player = PlayerModel(//id: playerId as! String,
                                                navn: (playerName as! String?)!,
                                                uke: (playerweek as! String?)!
                                              //  spillet: (playerPlayed as! String?)!,
                                             //   extra : (playerExtra as! String?)!,
                                             //   walkover: (playerWalkover as! String?)!,
                                             //   kommentar: (playerKommentar as! String?)!
                                                )
                       
                       self.playerList.append(player)
                   }
                   self.tableViewer.reloadData()
               }
               
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


