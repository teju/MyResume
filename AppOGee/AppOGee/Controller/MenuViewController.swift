//
//  MenuViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 06/05/21.
//  Copyright © 2021 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase
import PUGifLoading

class MenuViewController: UITableViewController ,TabItem,UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout{
    @IBOutlet weak var lbOurStory: UILabel!
    var refAboutUs = DatabaseReference()
    var aboutUsList = NSMutableArray()
    let loading = PUGIFLoading()
    @IBOutlet weak var collection_view: UICollectionView!
    
    @IBOutlet var mainTableView: UITableView!
    var tabImage: UIImage? {
      return UIImage(named: "About Us")
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        let bgImg =  UIImageView(image: UIImage(named: "about_us_bg.png"))
        bgImg.alpha = 0.6
        tableView.backgroundView = bgImg
        if #available(iOS 15.0, *) {
            tableView.sectionHeaderTopPadding = 0
        }
        loading.show("", gifimagename: "loading_animation",iWidth: 80,iHight: 80)
        collection_view.delegate = self
        collection_view.dataSource = self
        
        self.aboutUsList.removeAllObjects()
        refAboutUs = Database.database().reference().child("/aboutus");
        refAboutUs.observe(DataEventType.value, with: {(snapshot) in
               self.aboutUsList = snapshot.value as! NSMutableArray
               print(self.aboutUsList)
               self.collection_view.reloadData()
                self.loading.hide()

        })
        
        lbOurStory.text = "We are a digital design and mobile application development company that deliver great iconic logos and applications to our esteemed clients.\n\nWe collaborates with entrepreneurs and start up businesses to create unforgettable brands.\n\nWe are constantly building new products &amp; practical designs that generates long term profits for brand &amp; services that enables entrepreneurs to build profitable brand faster.\n\nWe cordially invite you to join us   and become a part of a growing family."
    }
    
    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return CGFloat.leastNonzeroMagnitude
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return aboutUsList.count
    }
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "about_us",
                                                     for: indexPath as IndexPath) as!   AboutUsTableViewCell
      
        let dict =  aboutUsList[indexPath.row] as! NSDictionary
        let imageURL = URL(string: dict["image"] as! String )
        cell.profilePhoto.sd_setImage(with:imageURL)
        
        cell.lbName.text = "Name: \( dict["Name"] as! String)"
        cell.lbLevel.text = "Level: \( dict["Level"] as! String)"
        cell.lbLevel.text = "Experience: \( dict["Experience"] as! String)"
        cell.profilePhoto.frame.size.width = 60
        cell.profilePhoto.frame.size.height = 60
        cell.lbDescription.text = "\( dict["Description"] as! String)"
        cell.profilebg.frame.size.width = 140
        cell.profilebg.frame.size.height = 140
        //cell.lbRole.text = dict["role"] as! String
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: UIScreen.main.bounds.width,height: 400)
    }
}
