//
//  ProjectsViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 28/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase
import PUGifLoading

class ProjectsViewController: UIViewController, UITableViewDataSource, UITableViewDelegate{

    var refProjects = DatabaseReference()
    var projectList = NSMutableArray()
    var isFromHome = false
    let loading = PUGIFLoading()
    var message = "Hi AppOGee,\nI want you to help me developing my project.\nCall me back!"
    var mobile = "919980588711"

    @IBOutlet weak var projectsTableview: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        projectsTableview.dataSource = self
        projectsTableview.delegate = self
      
    
    }
    override func viewDidAppear(_ animated: Bool) {
        if(isFromHome) {
            self.navigationController?.isNavigationBarHidden = false
        } else {
            self.navigationController?.isNavigationBarHidden = true
        }
        self.navigationItem.backBarButtonItem = UIBarButtonItem(title:"", style:.plain, target:nil, action:nil)
        
        
        if(ConnectionManager.shared.hasConnectivity()) {

            refProjects = Database.database().reference().child("/myApps");
                self.projectList.removeAllObjects()
            let bgImg =  UIImageView(image: UIImage(named: "features_bg.png"))
            bgImg.alpha = 0.6
            projectsTableview.backgroundView = bgImg
            loading.show("", gifimagename: "loading_animation",iWidth: 80,iHight: 80)

            refProjects.observe(DataEventType.value, with: {(snapshot) in
                   self.projectList = snapshot.value as! NSMutableArray
                   print(self.projectList)
                   self.projectsTableview.reloadData()
                self.loading.hide()

            })
        } else {
            loading.hide()
            let alert = UIAlertController(title: "No Internet Connection", message: "", preferredStyle: UIAlertController.Style.alert)
            alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
            self.present(alert, animated: true, completion: nil)
        }
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return  self.projectList.count
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let dict =  projectList[indexPath.row] as! NSDictionary
        let sms: String = dict["playstore_link"] as! String
        let strURL: String = sms.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        UIApplication.shared.open(URL.init(string: strURL)!, options: [:], completionHandler: nil)
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "projectscell",
                                                     for: indexPath as IndexPath) as! ProjectsTableViewCell
        let dict =  projectList[indexPath.row] as! NSDictionary
        let imageURL = URL(string: dict["logo"] as! String )

        cell.projectLogo.sd_setImage(with:imageURL)
        cell.projectName.text =  dict["app_name"] as! String
        cell.platform.text =  dict["Platform"] as! String
        cell.projectDescription.text =  dict["description"] as! String
        var color1 = hexStringToUIColor(hex: dict["bg_colour"] as! String)
        cell.bgview.backgroundColor = color1
        if(dict["text_colour"] as! String == "light") {
            cell.projectName.textColor = UIColor.white
            cell.platform.textColor = UIColor.white
            cell.projectDescription.textColor = UIColor.white
        } else {
            cell.projectName.textColor = UIColor.black
            cell.platform.textColor = UIColor.black
            cell.projectDescription.textColor = UIColor.black
        }
        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 160
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
func hexStringToUIColor (hex:String) -> UIColor {
    var cString:String = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()

    if (cString.hasPrefix("#")) {
        cString.remove(at: cString.startIndex)
    }

    if ((cString.count) != 6) {
        return UIColor.gray
    }

    var rgbValue:UInt64 = 0
    Scanner(string: cString).scanHexInt64(&rgbValue)

    return UIColor(
        red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
        green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
        blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
        alpha: CGFloat(1.0)
    )
}
