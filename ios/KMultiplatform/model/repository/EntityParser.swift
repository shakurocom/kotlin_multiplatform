import Foundation
import Alamofire
import SwiftyJSON
import KMulti

protocol EntityParser {
  associatedtype T
  
  func parse(response: DataResponse<Any>) -> T?
}

class GithubReposParser: EntityParser {
  
  typealias T = [KMultiGithubRepo]
  
  func parse(response: DataResponse<Any>) -> [KMultiGithubRepo]? {
    if let result = response.result.value {
      let jsonArray = JSON(result).arrayValue
      return jsonArray.map({json -> KMultiGithubRepo in
        return KMultiGithubRepo(
          id: json["id"].int64Value,
          name: json["name"].stringValue,
          branches: []
        )
      })
    } else {
      return nil
    }
  }
}

class GithubBranchesParser: EntityParser {
  
  typealias T = [KMultiGithubBranch]
  
  func parse(response: DataResponse<Any>) -> [KMultiGithubBranch]? {
    if let result = response.result.value {
      let jsonArray = JSON(result).arrayValue
      return jsonArray.map({json -> KMultiGithubBranch in
        let commitJson = json["commit"].dictionaryValue
        return KMultiGithubBranch(
          name: json["name"].stringValue,
          commitSha: (commitJson["sha"]?.stringValue ?? ""),
          commitUrl: (commitJson["url"]?.stringValue ?? "")
        )
      })
    } else {
      return nil
    }
  }
}

