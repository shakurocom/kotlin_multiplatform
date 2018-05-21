import Foundation

import Foundation
import Alamofire
import KMulti

class ReposRepository: KMultiReposRepository {
  
  let baseUrl: URL
  let githubUser: String
  let reposParser: GithubReposParser
  let branchesParser: GithubBranchesParser
  
  init(baseUrl: URL, githubUser: String, reposParser: GithubReposParser, branchesParser: GithubBranchesParser) {
    self.baseUrl = baseUrl
    self.githubUser = githubUser
    self.reposParser = reposParser
    self.branchesParser = branchesParser
  }
  
  override func getRepositories(callback: KMultiStdlibContinuation) {
    let url = baseUrl.appendingPathComponent("users/\(githubUser)/repos")
    Alamofire.request(url)
//      .debugLog()
      .responseJSON { response in
        if let result = self.reposParser.parse(response: response) {
          callback.resume(value: result)
        } else {
          callback.resumeWithException(exception: KMultiStdlibThrowable(message: "Can't parse github repositories"))
        }
    }
  }
  
  override func getBranches(repo: KMultiGithubRepo, callback: KMultiStdlibContinuation) {
    let url = baseUrl.appendingPathComponent("repos/\(githubUser)/\(repo.name)/branches")
    Alamofire.request(url)
//      .debugLog()
      .responseJSON { response in
        if let result = self.branchesParser.parse(response: response) {
          callback.resume(value: result)
        } else {
          callback.resumeWithException(exception: KMultiStdlibThrowable(message: "Can't parse github branches"))
        }
    }
  }
}

fileprivate extension Request {
  func debugLog() -> Self {
    #if DEBUG
      debugPrint(self)
    #endif
    return self
  }
}
