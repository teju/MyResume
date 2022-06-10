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
import PUGifLoading

class FeaturesViewController: UIViewController ,UITableViewDataSource, UITableViewDelegate{
    var itemText: String?
    let loading = PUGIFLoading()
    var message = "Hi AppOGee,\nI want you to help me developing my project.\nCall me back!"
    var mobile = "919980588711"

    @IBOutlet weak var featurestableview: UITableView!
    
    var refFeatures = DatabaseReference()
    var featuresList = NSMutableArray()
  
    override func viewDidLoad() {
        super.viewDidLoad()
    
        self.loadViewIfNeeded()
        featurestableview.delegate = self
        featurestableview.dataSource = self
        let bgImg =  UIImageView(image: UIImage(named: "features_bg.png"))
        bgImg.alpha = 0.6
        featurestableview.backgroundView = bgImg
        
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        loading.show("", gifimagename: "loading_animation",iWidth: 80,iHight: 80)

        refFeatures = Database.database().reference().child("features/scrollImages");
            self.featuresList.removeAllObjects()

        refFeatures.observe(DataEventType.value, with: {(snapshot) in
               self.featuresList = snapshot.value as! NSMutableArray
               print(self.featuresList)
               self.featurestableview.reloadData()
    
                self.loading.hide()

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
       // cell.featureImg.downloaded(from: featuresList[indexPath.row] as! String)
       // cell.featureImg?.sd_setImage(with: imageURL, completed: nil)

        return cell
       
    }
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let initialViewController = storyboard.instantiateViewController(withIdentifier: "WebDetailsViewController") as! WebDetailsViewController
        if(indexPath.row == 0) {
            initialViewController.imgurl = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature1.png?alt=media&token=4fd8c68b-f354-403e-9426-555ae79e6394"
            initialViewController.weburl = "https://appogee.in/remoteworks.html"
        } else  if(indexPath.row == 1) {
            initialViewController.weburl = "https://appogee.in/mobAppDev.html"
            initialViewController.imgurl = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature2.png?alt=media&token=67493d6f-217f-43c7-bd3e-384379387be8"
        } else if(indexPath.row == 2) {
            initialViewController.weburl = "https://appogee.in/webDesign.html"
            initialViewController.imgurl = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature3.png?alt=media&token=63e88873-2337-4065-b5b6-22b431f8f6dc"
        }  else if(indexPath.row == 3) {
            initialViewController.weburl = "https://appogee.in/idea.html"
            initialViewController.imgurl = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature4.png?alt=media&token=be70b148-a6fe-4909-a82a-06d67a4ce598"
        }  else if(indexPath.row == 4) {
            initialViewController.weburl = "https://appogee.in/suppMaintain.html"
            initialViewController.imgurl = "https://firebasestorage.googleapis.com/v0/b/appogee-2a96b.appspot.com/o/features%2FFeatures%2F%2BFeature%2FpFeature5.png?alt=media&token=5f58b8e2-1bee-4929-9018-7e3d3eacdbaa"

        }
        self.navigationController?.pushViewController(initialViewController, animated: true)

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


extension UIImageView {
    func downloaded(from url: URL, contentMode mode: ContentMode = .scaleAspectFit) {
        contentMode = mode
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard
                let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                let data = data, error == nil,
                let image = UIImage(data: data)
                else { return }
            DispatchQueue.main.async() { [weak self] in
                self?.image = image
            }
        }.resume()
    }
    func downloaded(from link: String, contentMode mode: ContentMode = .scaleAspectFit) {
        guard let url = URL(string: link) else { return }
        downloaded(from: url, contentMode: mode)
    }
}
