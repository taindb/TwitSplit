# TwitSplit
Assignment round in Dwarves Foundation

This project is a my interview assignment at Dwarves Foundation.

## Description
The product Tweeter allows users to post short messages limited to 50 characters each.
Sometimes, users get excited and write messages longer than 50 characters.
Instead of rejecting these messages, we would like to add a new feature that will split the message into parts and send multiple messages on the user's behalf, all of them meeting the 50 character requirement.

## Example
Suppose the user wants to send the following message:
"I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
This is 91 characters excluding the surrounding quotes. When the user presses send, it will send the following messages:
"1/2 I can't believe Tweeter now supports chunking" "2/2 my messages, so I don't have to do it myself."
Each message is now 49 characters, each within the allowed limit.

**note: Be aware that these count toward the character limit.

## Project Focus
Result returned from Slip message function in project is a List message with multiple part.

ex: ["1/2 I can't believe Tweeter now supports chunking", "2/2 my messages, so I don't have to do it myself."]

1. Split message function: Using recursive, re-calculate total part, re-render prefix indicator. [Here](https://github.com/taindb/TwitSplit/blob/master/app/src/main/java/com/taindb/twitsplit/utils/StringUtils.java)

2. Unit Test [Here](https://github.com/taindb/TwitSplit/blob/master/app/src/test/java/com/taindb/twitsplit/TwitSplitUnitTest.java)

3. MVP Architecture.

4. Message Screen: send and display message.
## Implementing the app
in your project, you separate the message into multipe part by splitMessage() method of StringUtils class.
The splitMessage() is a static method and can check the message error and error code to display an error message on UI.
 ```
        try {
            List<String> list = StringUtils.splitMessage(message, CHARACTER_LIMIT);
            ...
        } catch (StringUtils.SplitMessageException e) {
            e.printStackTrace();
            ....
        }
```

## License

Copyright (C) 2018 taindb

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
