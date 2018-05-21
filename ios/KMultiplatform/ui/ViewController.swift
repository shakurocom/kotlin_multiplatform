import UIKit
import KMulti

class ViewController: UITableViewController, KMultiReposView {
  
  var presenter: KMultiReposPresenter!
  var items = [KMultiGithubRepo]()
  
  override func awakeFromNib() {
    super.awakeFromNib()
    ViewControllerAssembly.instance().inject(into: self)
  }

  override func viewDidLoad() {
    super.viewDidLoad()
    tableView.separatorStyle = .none
  }

  override func viewWillAppear(_ animated: Bool) {
    super.viewWillAppear(animated)
    presenter.attach(view: self)
  }
  
  override func viewDidDisappear(_ animated: Bool) {
    super.viewDidDisappear(animated)
    presenter.detach()
  }

  func showRepoList(repoList: [KMultiGithubRepo]) {
    items.removeAll()
    items.append(contentsOf: repoList)
    tableView.reloadData()
  }
  
  func showLoading(loading: Bool) {
    if (loading) {
      refreshControl?.beginRefreshing()
      refreshControl?.isHidden = false
    } else {
      refreshControl?.endRefreshing()
    }
  }

  func showError(errorMessage: String) {
    // TODO
  }
  
  @IBAction func refresh(refreshControl: UIRefreshControl) {
    presenter.refresh()
  }
  
  override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return items.count
  }
  
  override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
    let cell = tableView.dequeueReusableCell(withIdentifier: "TitleCell", for: indexPath)
    let repo = items[indexPath.item]
    cell.textLabel?.text = repo.name
    cell.detailTextLabel?.text = "Branches: \(repo.branches.count)"
    return cell
  }
}

