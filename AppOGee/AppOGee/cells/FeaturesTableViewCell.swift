//
//  FeaturesTableViewCell.swift
//  AppOGee
//
//  Created by Tejaswini N on 26/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class FeaturesTableViewCell: UITableViewCell {

    @IBOutlet weak var lbl: UILabel!
    @IBOutlet weak var featureImg: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
