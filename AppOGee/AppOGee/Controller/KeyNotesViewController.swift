//
//  KeyNotesViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 28/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase
import PUGifLoading

class KeyNotesViewController: UIViewController  ,UITableViewDataSource, UITableViewDelegate{
  
    @IBOutlet weak var Keynotestableview: UITableView!
    let loading = PUGIFLoading()
    var message = "Hi AppOGee,\nI want you to help me developing my project.\nCall me back!"
    var mobile = "919980588711"

    var refKeynotes = DatabaseReference()
    var keyNotesList = NSMutableArray()
  

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = UIColor.white
        Keynotestableview.delegate = self
        Keynotestableview.dataSource = self
        let bgImg =  UIImageView(image: UIImage(named: "features_bg.png"))
        bgImg.alpha = 0.6
        Keynotestableview.backgroundView = bgImg
        loading.show("", gifimagename: "loading_animation",iWidth: 80,iHight: 80)

        refKeynotes = Database.database().reference().child("features/keynotes");
         self.keyNotesList.removeAllObjects()

        refKeynotes.observe(DataEventType.value, with: {(snapshot) in
            self.keyNotesList = snapshot.value as! NSMutableArray
            print(self.keyNotesList)
            self.Keynotestableview.reloadData()
            self.loading.hide()
        })
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
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        self.keyNotesList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Keynotescell",
                                                     for: indexPath as IndexPath) as! KeyNotesTableViewCell
        let dict =  keyNotesList[indexPath.row] as! NSDictionary
        let imageURL = URL(string: dict["image"] as! String )
        cell.bgImg.sd_setImage(with:imageURL)
        cell.lbTitle.text = dict["title"] as! String
        cell.lbDescription.attributedText = (dict["description"] as! String).html2AttributedString
        cell.lbDescription.textColor = UIColor.white
        cell.lbDescription.font = cell.lbDescription.font.withSize(16)

        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 450
    }
}
extension Data {
    var html2AttributedString: NSAttributedString? {
        do {
            return try NSAttributedString(data: self, options: [.documentType: NSAttributedString.DocumentType.html, .characterEncoding: String.Encoding.utf8.rawValue], documentAttributes: nil)
        } catch {
            print("error:", error)
            return  nil
        }
    }
    var html2String: String { html2AttributedString?.string ?? "" }
}
extension StringProtocol {
    var html2AttributedString: NSAttributedString? {
        Data(utf8).html2AttributedString
    }
    var html2String: String {
        html2AttributedString?.string ?? ""
    }
}
