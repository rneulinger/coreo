Feature: Examples
  Scenario: High level Generic

    * # And
    When onTo "File new"

    * goTo "abc"
    * # no parameter
    * # no parameter + table:
      | key | value |
      | Name| Doe   |
    * # no parameter + multiline text:
      """
      das ist ein
      mehrzeiliger
      text
      """

    * # one parameter, no trailing text "p1"
    * # one parameter, no trailing text + object "p1":
    |table or text|

    * # one parameter "p1" with trailing text
    * # one parameter "p1" with trailing text plus table:
    |table or text|

    * # two parameters "p1" no trailing text "p2"
    * # two parameters "p1" no trailing text + object "p2":
      |table or text|

    * # two parameters "p1" and "p2" with trailing text
    * # two parameters "p1" and "p2" with trailing text + object:
      |table or text|


