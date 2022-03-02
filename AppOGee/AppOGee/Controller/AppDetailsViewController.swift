//
//  AppDetailsViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 02/03/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class AppDetailsViewController: UITableViewController {
    @IBOutlet weak var imgLogo: UIImageView!
    @IBOutlet weak var bgview: UIView!
    
    @IBOutlet weak var lbDescription: UILabel!
    @IBOutlet weak var lbTitle: UILabel!
    
    var dict = NSDictionary()
    override func viewDidLoad() {
        super.viewDidLoad()
        bgview.roundCorners(corners: [.bottomLeft,.bottomRight], radius: 20)
        if #available(iOS 15.0, *) {
            tableView.sectionHeaderTopPadding = 0
        }
        tableView.estimatedRowHeight = 273.0
        tableView.rowHeight = UITableView.automaticDimension
        
        let imageURL = URL(string: dict["logo"] as! String )
        imgLogo.sd_setImage(with:imageURL)
        lbTitle.text = dict["app_name"] as! String
        lbDescription.text = dict["description"] as! String
        let color1 = hexStringToUIColor(hex: dict["app_colour"] as! String)
        bgview.backgroundColor = color1
        if(dict["text_colour"] as! String == "light") {
            lbTitle.textColor = UIColor.white
            lbDescription.textColor = UIColor.white
        } else {
            lbTitle.textColor = UIColor.black
            lbDescription.textColor = UIColor.black
        }
    }
    


}
extension String {
    func height(withConstrainedWidth width: CGFloat, font: UIFont) -> CGFloat {
        let constraintRect = CGSize(width: width, height: .greatestFiniteMagnitude)
        let boundingBox = self.boundingRect(with: constraintRect, options: .usesLineFragmentOrigin, attributes: [NSAttributedString.Key.font: font], context: nil)
    
        return ceil(boundingBox.height)
    }

    func width(withConstrainedHeight height: CGFloat, font: UIFont) -> CGFloat {
        let constraintRect = CGSize(width: .greatestFiniteMagnitude, height: height)
        let boundingBox = self.boundingRect(with: constraintRect, options: .usesLineFragmentOrigin, attributes: [NSAttributedString.Key.font: font], context: nil)

        return ceil(boundingBox.width)
    }
}
