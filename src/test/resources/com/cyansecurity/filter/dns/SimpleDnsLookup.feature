Feature: Simple DNS Lookup

  Scenario Outline: Lookup by Hostname

    Then Lookup By Hostname "<HOSTNAME>"

    Examples:
      | HOSTNAME                 |
      | "google.com"             |
      | "i-new.com"              |
      | "inew-cs.com"            |
      | "this doesnt exist.exif" |

  Scenario: Lookup by IP-Address

    Then Lookup By IP-Address "8.8.8.8"