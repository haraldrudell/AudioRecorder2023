<h1>Audio Recorder 2023</h1>

<p>Built by <strong>Harald Rudell</strong> harald.rudell@gmail.com on July 15, 2023, in San Francisco, CA</p>

<h2>0.9.33-230715</h2>
<p>0.9.33-230715 is the versionName visible in app Settings</p>
<p>934 is the internal Android versionCode</p>

<h2>Android 12–13–14+ Compatible</h2>
<ul>
<li>Telephony compatible with Android 12+</li>
<li>POST_NOTIFICATIONS permission for Android 13+</li>
<li>No static storage of Context — avoids memory leak</li>
<li>Supports software module Timber 5.0.1+</li>
<li>Sound Software-Engineering principles</li>
</ul>
<p />
<ul>
<li>Improving on Audio Recorder 0.9.33 March 19, 2023, hash a11bb9b:<ul>
<li>0.9.33 was created on January 29, 2022</li>
<li>Other committers pushed 6 commits over two years without increasing version number</li>
<li>0.9.33 releases from Google Play as of July 15, 2023, have two-year-old rollbacks with old crashes returning</li>
<li>0.9.26 from F-Droid as of July 15, 2023, is from 2021 with old crashes</li>
<li>Those crashes means any recording in progress is lost</li></ul>
</ul>

<p>—</p>

![Audio Recorder Logo](https://github.com/Dimowner/AudioRecorder/blob/master/app/src/releaseConfig/res/mipmap-xxxhdpi/audio_recorder_logo.png)

# Audio Recorder

<p><b>Audio recorder (Voice recorder, Sound recorder)</b> - an application that gives easy audio recording experience. 
This app is optimized to start up as fast as possible, aiding the user in not missing important sounds.</p>

[<img src="https://fdroid.gitlab.io/artwork/badge/get-it-on.png"
     alt="Get it on F-Droid"
     height="80">](https://f-droid.org/packages/com.dimowner.audiorecorder/)
[<img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png"
     alt="Get it on Google Play"
     height="80">](https://play.google.com/store/apps/details?id=com.dimowner.audiorecorder)

<b>Two recording formats are available:</b>
 - M4A
 - Waveform Audio File Format (WAVE, or WAV)

In settings, you can set sample rate, bitrate (for AAC only) and select between mono and stereo.
Selected preferences directly affect the file size of the recordings.

Colorful themes allow customizing the app look, making the experience better.

<b>Features:</b>
- Recording audio
- Playing back recordings
- Make recordings in M4A and WAV
- Settting sample rate and bitrate
- Recording and playing back in background
- Displaying waveform of the recording
- Rename recordings
- Share recordings
- Import audio files
- List recordings
- Add recordings to bookmarks
- Color themes

### License

```
Copyright 2019 Dmytro Ponomarenko

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.
```
