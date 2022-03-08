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
    var message = "Hi AppOGee,\nI want you to help me developing my project.\nCall me back!"
    var mobile = "919980588711"

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.delegate = self
        tableView.dataSource = self
        self.view.backgroundColor = UIColor.white
        tableView.contentInset = UIEdgeInsets(top: -20, left: 0, bottom: 0, right: 0);

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
    
}
