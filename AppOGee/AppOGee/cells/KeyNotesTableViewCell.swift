//
//  KeyNotesTableViewCell.swift
//  AppOGee
//
//  Created by Tejaswini N on 28/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class KeyNotesTableViewCell: UITableViewCell {

    @IBOutlet weak var lbDescription: UILabel!
    @IBOutlet weak var lbTitle: UILabel!
    @IBOutlet weak var bgImg: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
