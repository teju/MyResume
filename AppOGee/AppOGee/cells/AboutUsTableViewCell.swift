//
//  AboutUsTableViewCell.swift
//  AppOGee
//
//  Created by Tejaswini N on 02/03/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class AboutUsTableViewCell: UITableViewCell {

    @IBOutlet weak var lbLevel: UILabel!
    @IBOutlet weak var lbExperience: UILabel!
    @IBOutlet weak var lbName: UILabel!
    @IBOutlet weak var lbRole: UILabel!
    @IBOutlet weak var profilePhoto: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
