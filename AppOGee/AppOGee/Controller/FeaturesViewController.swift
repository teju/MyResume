//
//  FeaturesViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 25/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase
import SDWebImage

class FeaturesViewController: UIViewController ,UITableViewDataSource, UITableViewDelegate{
    var itemText: String?

    @IBOutlet weak var featurestableview: UITableView!
    
    var refFeatures = DatabaseReference()
    var featuresList = NSMutableArray()
  
    override func viewDidLoad() {
        super.viewDidLoad()
    
        self.loadViewIfNeeded()
        featurestableview.delegate = self
        featurestableview.dataSource = self
        featurestableview.backgroundView = UIImageView(image: UIImage(named: "features_bg.png"))

    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        refFeatures = Database.database().reference().child("features/scrollImages");
            self.featuresList.removeAllObjects()

        refFeatures.observe(DataEventType.value, with: {(snapshot) in
               self.featuresList = snapshot.value as! NSMutableArray
               print(self.featuresList)
               self.featurestableview.reloadData()
              self.featurestableview.backgroundView = UIImageView(image: UIImage(named: "features_bg.png"))

           })
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return  self.featuresList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "featurescell",
                                                     for: indexPath as IndexPath) as! FeaturesTableViewCell
        let imageURL = URL(string:featuresList[indexPath.row] as! String )
        
        cell.featureImg.sd_setImage(with:imageURL)

        return cell
    }

}


