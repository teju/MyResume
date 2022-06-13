//
//  PhotoFullViewViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 02/03/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class PhotoFullViewViewController: UIViewController ,UICollectionViewDelegate,UICollectionViewDataSource,UICollectionViewDelegateFlowLayout {
    var releated_images = NSMutableArray()
    @IBOutlet weak var lbTitle: UILabel!
    var strTitle = ""
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        releated_images.count
    }
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "releated_images",
                                                     for: indexPath as IndexPath) as!   ReleatedImagesCollectionViewCell
        let bgImg =  UIImageView(image: UIImage(named: "features_bg.png"))
        bgImg.alpha = 0.6
        cell.backgroundView = bgImg
        let imageURL = URL(string: releated_images[indexPath.row] as! String )
        cell.releatedimgView.sd_setImage(with:imageURL)
        return cell
    }
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: UIScreen.main.bounds.width ,height: UIScreen.main.bounds.height - 100)
    }
    @IBOutlet weak var collection_view: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        collection_view.delegate = self
        collection_view.dataSource = self
        lbTitle.text = strTitle


    }
    override func viewDidAppear(_ animated: Bool) {
        self.navigationController?.isNavigationBarHidden = true
    }
    @IBAction func back(_ sender: Any) {
        self.navigationController?.popViewController(animated: true)
        self.navigationItem.backBarButtonItem = UIBarButtonItem(title:"", style:.plain, target:nil, action:nil)
    }
}
