//
//  FeaturesViewController.swift
//  AppOGee
//
//  Created by Tejaswini N on 25/02/22.
//  Copyright © 2022 Tejaswini N. All rights reserved.
//

import UIKit

class FeaturesTabViewController: UIViewController ,TabItem{

    @IBOutlet weak var tabview: UIView!
    var tabs = [ViewPagerTab]()
    var options: ViewPagerOptions?
    var pager:ViewPager?
    var tabImage: UIImage? {
      return UIImage(named: "Asset 2")
    }
    var tabs3 = [
        ViewPagerTab(title: "Features", image: UIImage(named: "features")),
        ViewPagerTab(title: "Keynotes", image: UIImage(named: "keynote")),
        ViewPagerTab(title: "Projects", image: UIImage(named: "works")),
    ]
   
    
    override func viewDidLoad() {
        super.viewDidLoad()
        displayViewPager()
        
        guard let options = self.options else { return }
        pager = ViewPager(viewController: self,containerView: tabview)
        pager?.setOptions(options: options)
        pager?.setDataSource(dataSource: self)
        pager?.setDelegate(delegate: self)
        pager?.build()
        
    }
    

    func displayViewPager() {
    
        options = ViewPagerOptions()
        tabs = [ViewPagerTab]()
        tabs = tabs3
        options!.tabType = .imageWithText
        options!.isTabIndicatorAvailable = false
        options!.isTabBarShadowAvailable = false
        options!.tabViewHeight = 80
        options!.tabViewImageSize =  CGSize(width: 30, height: 30)
        options!.distribution = .equal
        

    }
    
}
extension FeaturesTabViewController: ViewPagerDataSource {
    
    func numberOfPages() -> Int {
        return tabs.count
    }
    
    func viewControllerAtPosition(position:Int) -> UIViewController {
        if(position == 0) {
            let vc = self.storyboard?.instantiateViewController(withIdentifier: "FeaturesViewController") as! FeaturesViewController
            vc.itemText = "\(tabs[position].title)"
            return vc
        } else if(position == 1) {
            let vc = self.storyboard?.instantiateViewController(withIdentifier: "KeyNotesViewController") as! KeyNotesViewController
            return vc
        } else {
            let vc = self.storyboard?.instantiateViewController(withIdentifier: "ProjectsViewController") as! ProjectsViewController
            vc.isFromHome = false
            return vc
        }

    }
    
    func tabsForPages() -> [ViewPagerTab] {
        return tabs
    }
    
    func startViewPagerAtIndex() -> Int {
        return 0
    }
}

extension FeaturesTabViewController: ViewPagerDelegate {
    
    func willMoveToControllerAtIndex(index:Int) {
        print("Moving to page \(index)")
    }
    
    func didMoveToControllerAtIndex(index: Int) {
        print("Moved to page \(index)")
    }
}
