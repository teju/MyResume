//
//  WorkTabViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 31/05/20.
//  Copyright © 2020 Tejaswini N. All rights reserved.
//

import UIKit
import AMTabView
import ARNTransitionAnimator

class WorkTabViewController: ImageZoomAnimationVC ,TabItem, UICollectionViewDelegate, UICollectionViewDataSource{
    weak var selectedImageView : UIImageView?
       
       var animator : ARNTransitionAnimator?
       
       var isModeModal : Bool = false
       var isModeInteractive : Bool = false
    var tabImage: UIImage? {
         return UIImage(named: "dummy")
       }
    
    @IBOutlet weak var collectionView: UICollectionView!
    fileprivate var items = [Character]()
       
       fileprivate var currentPage: Int = 0 {
           didSet {
               let character = self.items[self.currentPage]
           }
       }
       
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
           self.items = self.createItems()
           
           self.currentPage = 0
           
           NotificationCenter.default.addObserver(self, selector: #selector(WorkTabViewController.rotationDidChange), name: UIDevice.orientationDidChangeNotification, object: nil)
       }
       
       fileprivate func setupLayout() {
           let layout = self.collectionView.collectionViewLayout as! UPCarouselFlowLayout
           layout.spacingMode = UPCarouselFlowLayoutSpacingMode.overlap(visibleOffset: 30)
       }
       
       fileprivate func createItems() -> [Character] {
           let characters = [
               Character(imageName: "dummy", name: "Wall-E", movie: "Wall-E"),
               Character(imageName: "dummy", name: "Nemo", movie: "Finding Nemo"),
               Character(imageName: "dummy", name: "Remy", movie: "Ratatouille"),
               Character(imageName: "dummy", name: "Buzz Lightyear", movie: "Toy Story"),
               Character(imageName: "dummy", name: "Mike & Sullivan", movie: "Monsters Inc.")]
           return characters
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
           return items.count
       }
       
       func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
           let cell = collectionView.dequeueReusableCell(withReuseIdentifier: CarouselCollectionViewCell.identifier, for: indexPath) as! CarouselCollectionViewCell
           let character = items[(indexPath as NSIndexPath).row]
           cell.image.image = UIImage(named: character.imageName)
           return cell
       }
       
       func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
           let cell = collectionView.cellForItem(at: indexPath) as! CarouselCollectionViewCell
           self.selectedImageView = cell.image
           
           self.handleTransition()
       }

       
       // MARK: - UIScrollViewDelegate
       
       func scrollViewDidEndDecelerating(_ scrollView: UIScrollView) {
           let layout = self.collectionView.collectionViewLayout as! UPCarouselFlowLayout
           let pageSide = (layout.scrollDirection == .horizontal) ? self.pageSize.width : self.pageSize.height
           let offset = (layout.scrollDirection == .horizontal) ? scrollView.contentOffset.x : scrollView.contentOffset.y
           currentPage = Int(floor((offset - pageSide / 2) / pageSide) + 1)

            if(currentPage == items.count - 1 ) {
                currentPage = 0
                let indexPath = IndexPath(item: currentPage, section: 0)
                  let scrollPosition: UICollectionView.ScrollPosition = orientation.isPortrait ? .centeredHorizontally : .centeredVertically
                  self.collectionView.scrollToItem(at: indexPath, at: scrollPosition, animated: false)
            }
       }
    
       
       
       func handleTransition() {
           let storyboard = UIStoryboard(name: "Main", bundle: nil)
           let controller = storyboard.instantiateViewController(withIdentifier: "WorkListViewController") as! WorkListViewController
           self.navigationController?.pushViewController(controller, animated: true)

//           let animation = ImageZoomAnimation<ImageZoomAnimationVC>(rootVC: self, modalVC: controller, rootNavigation: self.navigationController)
//           let animator = ARNTransitionAnimator(duration: 0.5, animation: animation)
//           
//           let gestureHandler = TransitionGestureHandler(targetView: controller.view, direction: .bottom)
//           animator.registerInteractiveTransitioning(.dismiss, gestureHandler: gestureHandler)
//           
//           controller.transitioningDelegate = animator
//           self.animator = animator
           
           
//           let storyboard = UIStoryboard(name: "Main", bundle: nil)
//           let controller = storyboard.instantiateViewController(withIdentifier: "WorkListViewController") as! WorkListViewController
////
////           let animation = ImageZoomAnimation<ImageZoomAnimationVC>(rootVC: self, modalVC: controller)
////           let animator = ARNTransitionAnimator(duration: 0.5, animation: animation)
////           self.navigationController?.delegate = animator
////           self.animator = animator
//
//           self.navigationController?.pushViewController(controller, animated: true)
       }
       
       

       // MARK: - ImageTransitionZoomable
       
       override func createTransitionImageView() -> UIImageView {
           let imageView = UIImageView(image: self.selectedImageView!.image)
           imageView.contentMode = self.selectedImageView!.contentMode
           imageView.clipsToBounds = true
           imageView.isUserInteractionEnabled = false
           imageView.frame = self.selectedImageView!.convert(self.selectedImageView!.frame, to: self.view)
           
           return imageView
       }
       
       @objc override func presentationBeforeAction() {
           self.selectedImageView?.isHidden = true
       }
       
       override func presentationCompletionAction(didComplete: Bool) {
           self.selectedImageView?.isHidden = true
       }
       
       @objc override func dismissalBeforeAction() {
           self.selectedImageView?.isHidden = true
       }
       
       override func dismissalCompletionAction(didComplete: Bool) {
           self.selectedImageView?.isHidden = false
       }
}
