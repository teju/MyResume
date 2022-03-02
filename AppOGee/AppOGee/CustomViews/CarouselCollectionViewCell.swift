//
//  CarouselCollectionViewCell.swift
//  UPCarouselFlowLayoutDemo
//
//  Created by Paul Ulric on 23/06/2016.
//  Copyright © 2016 Paul Ulric. All rights reserved.
//

import UIKit

class CarouselCollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var image: UIImageView!
    static let identifier = "CarouselCollectionViewCell"
    
    @IBOutlet weak var btnViewAll: UIButton!
    @IBOutlet weak var arrow_up: UIImageView!
    @IBOutlet weak var bgView: UIView!
    @IBOutlet weak var lbDescription: UILabel!
    @IBOutlet weak var lbtitle: UILabel!
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
    }
}
