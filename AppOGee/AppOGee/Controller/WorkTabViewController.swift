//
//  WorkTabViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 31/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit
import Firebase

class WorkTabViewController: UIViewController ,TabItem, UICollectionViewDelegate, UICollectionViewDataSource{
    weak var selectedImageView : UIImageView?
    var refProjects = DatabaseReference()
    
    var tabImage: UIImage? {
         return UIImage(named: "Projects")
       }
    
    @IBOutlet weak var collectionView: UICollectionView!
    fileprivate var items = NSMutableArray()
       
       fileprivate var currentPage: Int = 0
       
       fileprivate var pageSize: CGSize {
           let layout = self.collectionView.collectionViewLayout as! UPCarouselFlowLayout
           var pageSize = layout.itemSize
           if layout.scrollDirection == .horizontal {
               pageSize.width += layout.minimumLineSpacing
           } else {
               pageSize.height += layout.minimumLineSpacing
           }
           return pageSize
       }
       
       fileprivate var orientation: UIDeviceOrientation {
           return UIDevice.current.orientation
       }
       
       
       override func viewDidLoad() {
           super.viewDidLoad()
           self.extendedLayoutIncludesOpaqueBars = false
        
           self.setupLayout()
           self.currentPage = 0
           
           NotificationCenter.default.addObserver(self, selector: #selector(WorkTabViewController.rotationDidChange), name: UIDevice.orientationDidChangeNotification, object: nil)
           
           refProjects = Database.database().reference().child("/AppDetails");
               self.items.removeAllObjects()
           collectionView.backgroundView = UIImageView(image: UIImage(named: "features_bg.png"))

           refProjects.observe(DataEventType.value, with: {(snapshot) in
                  self.items = snapshot.value as! NSMutableArray
                  print(self.items)
                  self.collectionView.reloadData()
           })
       
       }
       
       fileprivate func setupLayout() {
           let layout = self.collectionView.collectionViewLayout as! UPCarouselFlowLayout
           layout.spacingMode = UPCarouselFlowLayoutSpacingMode.overlap(visibleOffset: 30)
       }
       
      
       @objc fileprivate func rotationDidChange() {
           guard !orientation.isFlat else { return }
           let layout = self.collectionView.collectionViewLayout as! UPCarouselFlowLayout
           let direction: UICollectionView.ScrollDirection = orientation.isPortrait ? .horizontal : .vertical
           layout.scrollDirection = direction
           if currentPage > 0 {
               let indexPath = IndexPath(item: currentPage, section: 0)
               let scrollPosition: UICollectionView.ScrollPosition = orientation.isPortrait ? .centeredHorizontally : .centeredVertically
               self.collectionView.scrollToItem(at: indexPath, at: scrollPosition, animated: false)
           }
       }
       
       // MARK: - Card Collection Delegate & DataSource
       
       func numberOfSections(in collectionView: UICollectionView) -> Int {
           return 1
       }
       
       func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
           return items.count + 1
       }
    
        @objc func viewAll() {
            let storyboard = UIStoryboard(name: "Main", bundle: nil)
            let initialViewController = storyboard.instantiateViewController(withIdentifier: "ProjectsViewController") as! ProjectsViewController
            initialViewController.isFromHome = true
            self.navigationController?.pushViewController(initialViewController, animated: true)
        }

       func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
           let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CarouselCollectionViewCell.identifier, for: indexPath) as! CarouselCollectionViewCell
           cell.btnViewAll.setTitle("", for: .normal)
           if(indexPath.row == items.count) {
               cell.bgView.isHidden = true
               cell.image.isHidden = true
               cell.btnViewAll.isHidden = false
           } else {
               cell.bgView.isHidden = false
               cell.image.isHidden = false
               cell.btnViewAll.isHidden = true
               let dict =  items[indexPath.row] as! NSDictionary
               let imageURL = URL(string: dict["logo"] as! String )
               cell.image.sd_setImage(with:imageURL)
               cell.lbtitle.text = dict["app_name"] as! String
               cell.lbDescription.text = dict["description"] as! String
               let color1 = hexStringToUIColor(hex: dict["app_colour"] as! String)
               cell.bgView.backgroundColor = color1
               if(dict["text_colour"] as! String == "light") {
                   cell.lbtitle.textColor = UIColor.white
                   cell.lbDescription.textColor = UIColor.white
                   cell.arrow_up.image = UIImage(named: "chevron-up.png")
               } else {
                   cell.lbtitle.textColor = UIColor.black
                   cell.lbDescription.textColor = UIColor.black
                   cell.arrow_up.image = UIImage(named: "chevron-up_black.png")

               }
               cell.btnViewAll.addTarget(self, action: #selector(viewAll), for: .touchUpInside)

           }
           return cell
       }
       
       func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
           if(indexPath.row == items.count) {
               let storyboard = UIStoryboard(name: "Main", bundle: nil)
               let initialViewController = storyboard.instantiateViewController(withIdentifier: "ProjectsViewController") as! ProjectsViewController
               initialViewController.isFromHome = true
               self.navigationController?.pushViewController(initialViewController, animated: true)
           } else {
               handleTransition()
           }
       }

       
       // MARK: - UIScrollViewDelegate
       
       func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
           let layout = self.collectionView.collectionViewLayout as! UPCarouselFlowLayout
           let pageSide = (layout.scrollDirection == .horizontal) ? self.pageSize.width : self.pageSize.height
           let offset = (layout.scrollDirection == .horizontal) ? scrollView.contentOffset.x : scrollView.contentOffset.y
           currentPage = Int(floor((offset - pageSide / 2) / pageSide) + 1)

//            if(currentPage == items.count) {
//                currentPage = 0
//                let indexPath = IndexPath(item: currentPage, section: 0)
//                  let scrollPosition: UICollectionView.ScrollPosition = orientation.isPortrait ? .centeredHorizontally : .centeredVertically
//                  self.collectionView.scrollToItem(at: indexPath, at: scrollPosition, animated: false)
//            }
       }
    
       
       
       func handleTransition() {
          let storyboard = UIStoryboard(name: "Main", bundle: nil)
          let controller = storyboard.instantiateViewController(withIdentifier: "WorkListViewController") as! WorkListViewController
          self.navigationController?.pushViewController(controller, animated: true)
       }
       
       

       
}
