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

    @IBOutlet weak var projectsTableview: UITableView!
    override func viewDidLoad() {
        super.viewDidLoad()
        projectsTableview.dataSource = self
        projectsTableview.delegate = self
        

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
    
    }
    override func viewDidAppear(_ animated: Bool) {
        if(isFromHome) {
            self.navigationController?.isNavigationBarHidden = false
        } else {
            self.navigationController?.isNavigationBarHidden = true
        }
        self.navigationItem.backBarButtonItem = UIBarButtonItem(title:"", style:.plain, target:nil, action:nil)
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return  self.projectList.count
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
