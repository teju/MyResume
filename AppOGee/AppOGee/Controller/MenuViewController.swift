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
    var message = "Hi AppOGee,\nI want you to help me developing my project.\nCall me back!"
    var mobile = "919980588711"

    @IBOutlet weak var lbBottomText: UILabel!
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
       
        
        lbOurStory.text = "We are a digital design and mobile application development company that deliver great iconic logos and applications to our esteemed clients.\n\nWe collaborates with entrepreneurs and start up businesses to create unforgettable brands.\n\nWe are constantly building new products &amp; practical designs that generates long term profits for brand &amp; services that enables entrepreneurs to build profitable brand faster.\n\nWe cordially invite you to join us   and become a part of a growing family."
        
        lbBottomText.text = "Get in touch\nBusiness: zeeshan@appogee.in / +91 9980588711\nGeneral: vision@appogee.in / +91 9980588711\n\nFind Us\nScott Matrix, Brickfield, Kualalumpur, Malaysia 50470\n26, Lingarajapuram, Bangalore, Karnataka 560084\nJade Height, Kakkanad, Kochi, Kerala 682030\n\nFollow us on:"
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        if(ConnectionManager.shared.hasConnectivity()) {

            refAboutUs = Database.database().reference().child("/aboutus");
            refAboutUs.observe(DataEventType.value, with: {(snapshot) in
                   self.aboutUsList = snapshot.value as! NSMutableArray
                   print(self.aboutUsList)
                   self.collection_view.reloadData()
                self.tableView.reloadData()
                    self.loading.hide()

            })
        } else {
            loading.hide()
            let alert = UIAlertController(title: "No Internet Connection", message: "", preferredStyle: UIAlertController.Style.alert)
            alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
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
        cell.lbExperience.text = "Experience: \( dict["Experience"] as! String)"
        cell.profilePhoto.frame.size.width = 60
        cell.profilePhoto.frame.size.height = 60
        cell.lbDescription.text = "\( dict["Description"] as! String)"
        cell.profilebg.frame.size.width = 140
        cell.profilebg.frame.size.height = 140
        cell.lbRole.text = "\(dict["role"] as! String)"
        cell.lbRole.textInsets = UIEdgeInsets(top: 2, left: 10, bottom: 2, right: 10)
        cell.lbDescription.sizeToFit()

        return cell
    }
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if(indexPath.section == 1) {
            return CGFloat(self.aboutUsList.count * 450) + 100
        }
        
        return super.tableView(tableView, heightForRowAt: indexPath)

    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: UIScreen.main.bounds.width,height: 450)
    }
    @IBAction func phone(_ sender: Any) {
        if let url = URL(string: "tel://\(mobile)") {
             UIApplication.shared.openURL(url)
         }
    }
    
    @IBAction func whatsapp(_ sender: Any) {
        let url : NSString = "https://api.whatsapp.com/send?phone=\(mobile)&text=\(message)" as NSString
        let urlStr : NSString = url.addingPercentEscapes(using: String.Encoding.utf8.rawValue)! as NSString
        let whatsappURL : NSURL = NSURL(string: urlStr as String)!
        
        if UIApplication.shared.canOpenURL(whatsappURL as URL) {
            UIApplication.shared.openURL(whatsappURL as URL)
        }
    }
    @IBAction func telegram(_ sender: Any) {
        let whatsappURL = URL.init(string: "tg://resolve?domain=@DAppOGee")
        if UIApplication.shared.canOpenURL(whatsappURL! as URL) {
            UIApplication.shared.openURL(whatsappURL! as URL)
        }
    }
    @IBAction func twitter(_ sender: Any) {
        let sms: String = "sms:\(mobile)&body=\(message)"
        let strURL: String = sms.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        UIApplication.shared.open(URL.init(string: strURL)!, options: [:], completionHandler: nil)

    }
    
    @IBAction func gmail(_ sender: Any) {
        let googleUrlString = "googlegmail:///co?subject=Call me back!&body=\(message)&to=zeeshan@appogee.in" as NSString
        let urlStr : NSString = googleUrlString.addingPercentEscapes(using: String.Encoding.utf8.rawValue)! as NSString
        let whatsappURL : NSURL = NSURL(string: urlStr as String)!
        
        if UIApplication.shared.canOpenURL(whatsappURL as URL) {
            UIApplication.shared.openURL(whatsappURL as URL)
        }
    }
    @IBAction func twitterUrl(_ sender: Any) {
        let sms: String = "https://twitter.com/dAppOGee"
        let strURL: String = sms.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        UIApplication.shared.open(URL.init(string: strURL)!, options: [:], completionHandler: nil)
    }
    @IBAction func facebook(_ sender: Any) {
        let sms: String = "https://www.facebook.com/OfficialAppogee"
        let strURL: String = sms.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        UIApplication.shared.open(URL.init(string: strURL)!, options: [:], completionHandler: nil)
    }
    @IBAction func insta(_ sender: Any) {
        let sms: String = "https://www.instagram.com/officialappogee/"
        let strURL: String = sms.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        UIApplication.shared.open(URL.init(string: strURL)!, options: [:], completionHandler: nil)
    }
    
    @IBAction func linked(_ sender: Any) {
        let sms: String = "https://www.linkedin.com/company/dappogee/"
        let strURL: String = sms.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        UIApplication.shared.open(URL.init(string: strURL)!, options: [:], completionHandler: nil)
    }
}
class EdgeInsetLabel: UILabel {
    var textInsets = UIEdgeInsets.zero {
        didSet { invalidateIntrinsicContentSize() }
    }

    override func textRect(forBounds bounds: CGRect, limitedToNumberOfLines numberOfLines: Int) -> CGRect {
        let textRect = super.textRect(forBounds: bounds, limitedToNumberOfLines: numberOfLines)
        let invertedInsets = UIEdgeInsets(top: -textInsets.top,
                                          left: -textInsets.left,
                                          bottom: -textInsets.bottom,
                                          right: -textInsets.right)
        return textRect.inset(by: invertedInsets)
    }

    override func drawText(in rect: CGRect) {
        super.drawText(in: rect.inset(by: textInsets))
    }
}
