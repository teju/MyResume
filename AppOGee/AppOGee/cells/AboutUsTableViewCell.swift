//
//  AboutUsTableViewCell.swift
//  AppOGee
//
//  Created by Tejaswini N on 02/03/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class AboutUsTableViewCell: UICollectionViewCell {
    @IBOutlet weak var profilebg: UIImageView!
    
    @IBOutlet weak var lbRole: EdgeInsetLabel!
    @IBOutlet weak var lbDescription: UILabel!
    @IBOutlet weak var lbLevel: UILabel!
    @IBOutlet weak var lbExperience: UILabel!
    @IBOutlet weak var lbName: UILabel!
    @IBOutlet weak var profilePhoto: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    

}
