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

class MenuViewController: UITableViewController ,TabItem{
    @IBOutlet weak var lbOurStory: UILabel!
    @IBOutlet weak var about_us_tableView: UITableView!
    var refAboutUs = DatabaseReference()
    var aboutUsList = NSMutableArray()
    let loading = PUGIFLoading()

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

        refAboutUs = Database.database().reference().child("/aboutus");
        self.aboutUsList.removeAllObjects()

        refAboutUs.observe(DataEventType.value, with: {(snapshot) in
               self.aboutUsList = snapshot.value as! NSMutableArray
               print(self.aboutUsList)
               self.about_us_tableView.reloadData()
                self.loading.hide()

        })
        about_us_tableView.dataSource = self
        about_us_tableView.delegate = self
        about_us_tableView.estimatedRowHeight = 273.0
        about_us_tableView.rowHeight = UITableView.automaticDimension
        lbOurStory.text = "We are a digital design and mobile application development company that deliver great iconic logos and applications to our esteemed clients.\n\nWe collaborates with entrepreneurs and start up businesses to create unforgettable brands.\n\nWe are constantly building new products &amp; practical designs that generates long term profits for brand &amp; services that enables entrepreneurs to build profitable brand faster.\n\nWe cordially invite you to join us   and become a part of a growing family."
    }
    
    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return CGFloat.leastNonzeroMagnitude
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = about_us_tableView.dequeueReusableCell(withIdentifier: "about_us",
                                                     for: indexPath as IndexPath) as! AboutUsTableViewCell
        let dict =  aboutUsList[indexPath.row] as! NSDictionary
        let imageURL = URL(string: dict["image"] as! String )
        cell.lbName.text = "Name : \(dict["Name"] as! String )"
        cell.lbLevel.text = "Level : \(dict["Level"] as! String )"
        cell.lbExperience.text = "Experience : \(dict["Experience"] as! String )"
        cell.lbRole.text = (dict["role"] as! String )
        cell.profilePhoto.sd_setImage(with:imageURL)
        return cell
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if(tableView == about_us_tableView) {
            return aboutUsList.count
        } else {
            return super.tableView(tableView, numberOfRowsInSection: section)
        }
    }
//
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if(tableView == about_us_tableView) {
            return UITableView.automaticDimension
        }
        return super.tableView(tableView, heightForRowAt: indexPath)

    }
    
    func rotate2(imageView: UIImageView, aCircleTime: Double) { //UIView
     
         UIView.animate(withDuration: aCircleTime/5, delay: 0.0, options: .curveLinear, animations: {
             imageView.transform = CGAffineTransform(rotationAngle: CGFloat(Double.pi))
         }, completion: { finished in
             UIView.animate(withDuration: aCircleTime/5, delay: 0.0, options: .curveLinear, animations: {
                 imageView.transform = CGAffineTransform(rotationAngle: CGFloat(Double.pi*2))
             }, completion: { finished in
                 self.rotate2(imageView: imageView, aCircleTime: aCircleTime)
             })
         })
    }
    
    func zoomOut(imageview : UIImageView) {
        UIView.animate(withDuration: 1.0, delay: 0.0, options: UIView.AnimationOptions.curveEaseOut, animations: {
                // HERE
            imageview.transform = CGAffineTransform.identity.scaledBy(x: 3, y: 3) // Scale your image

            }) { (finished) in
                 //imageview.transform = CGAffineTransform.identity // undo in 1 seconds

                
        }
    }
    
    func nukeAllAnimations(imageview : UIImageView) {
         imageview.layer.removeAllAnimations()
         imageview.layoutIfNeeded()
    }
    
    @IBAction func image1_click(_ sender: Any) {
       

    }

}
