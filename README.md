# android-embedded-activity-example

Activity Embedding Example

Example shows a usage of Activity Embedding feature. All screen components are separate Android activities instances. 

Phone:

https://github.com/vshpyrka/android-embedded-activity-example/assets/2741602/1b8afecc-5e61-43e6-a9c4-90c82c6387c6

Tablet:

https://github.com/vshpyrka/android-embedded-activity-example/assets/2741602/f40dfe08-9a26-4a03-b214-fc0afd2c3ed9

[main_split_config.xml](https://github.com/vshpyrka/android-embedded-activity-example/blob/main/src/main/res/xml/main_split_config.xml) - Configuration file which contains information about how activities should be split.

[ChatPlaceholderActivity](https://github.com/vshpyrka/android-embedded-activity-example/blob/main/src/main/java/com/example/embeddedactivity/ChatPlaceholderActivity.kt) - Represents an activity placeholder which is displayed when nothing is added to a task stack yet.

_SplitPairFilter_ tag contains declared activities which should be split
```
<SplitPairFilter
    window:primaryActivityName="com.example.embeddedactivity.ChatListActivity"
    window:secondaryActivityName="com.example.embeddedactivity.ChatActivity">
</SplitPairFilter>
```

`window:alwaysExpand="true"` - marks which activity should not be split and expanded to a full screen mode.
```
<ActivityRule
    window:alwaysExpand="true">
    <ActivityFilter
        window:activityName="com.example.embeddedactivity.ImagePreviewActivity"/>
</ActivityRule>
```

`window:splitLayoutDirection="locale"` - Attibute helps to use device locale to make activity split based on current device locale set.
RTL Example:

https://github.com/vshpyrka/android-embedded-activity-example/assets/2741602/38d876d6-eb66-42f9-81e4-afcd0e321f56


More information about [Activity Embedding](https://developer.android.com/guide/topics/large-screens/activity-embedding?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Factivity-embedding%23article-https%3A%2F%2Fdeveloper.android.com%2Fguide%2Ftopics%2Flarge-screens%2Factivity-embedding#additional_resources)
