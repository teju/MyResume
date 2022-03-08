//
//  AppDetailsViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 02/03/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class AppDetailsViewController: UITableViewController ,UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout{
    var releated_images = NSMutableArray()
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return releated_images.count
    }
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "releated_images",
                                                     for: indexPath as IndexPath) as!   ReleatedImagesCollectionViewCell
        let imageURL = URL(string: releated_images[indexPath.row] as! String )
        cell.releatedimgView.sd_setImage(with:imageURL)

        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: 150,height: 250)
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let storyboard = UIStoryboard(name: "Main", bundle: nil)
        let controller = storyboard.instantiateViewController(withIdentifier: "PhotoFullViewViewController") as! PhotoFullViewViewController
        controller.releated_images = releated_images
        self.navigationController?.pushViewController(controller, animated: true)
    }
    
    
    @IBOutlet weak var imgLogo: UIImageView!
    @IBOutlet weak var bgview: UIView!
    
    @IBOutlet weak var lbGallery: UILabel!
    @IBOutlet weak var lbDescription: UILabel!
    @IBOutlet weak var lbTitle: UILabel!
    
    @IBOutlet weak var collectionview: UICollectionView!
    var dict = NSDictionary()
    override func viewDidLoad() {
        super.viewDidLoad()
        bgview.roundCorners(corners: [.bottomLeft,.bottomRight], radius: 20)
        let bgImg =  UIImageView(image: UIImage(named: "features_bg.png"))
        bgImg.alpha = 0.6
        collectionview.backgroundView = bgImg

        tableView.contentInset = UIEdgeInsets(top: -20, left: 0, bottom: 0, right: 0);

        if #available(iOS 15.0, *) {
            tableView.sectionHeaderTopPadding = 0
        }
        tableView.estimatedRowHeight = 273.0
        tableView.rowHeight = UITableView.automaticDimension
        collectionview.delegate = self
        collectionview.dataSource = self
        
        let imageURL = URL(string: dict["logo"] as! String )
        imgLogo.sd_setImage(with:imageURL)
        lbTitle.text = dict["app_name"] as! String
        lbGallery.text = "\( dict["app_name"] as! String) Gallery"
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
        releated_images =  dict["releated_images"] as! NSMutableArray
        collectionview.reloadData()
    }
    
    @IBAction func back(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
    }
    override func viewDidAppear(_ animated: Bool) {
        //self.navigationController?.isNavigationBarHidden = false
        self.navigationItem.backBarButtonItem = UIBarButtonItem(title:"", style:.plain, target:nil, action:nil)
    }
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if(indexPath.row == 0) {
            return UITableView.automaticDimension

        } else {
            return 500
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
