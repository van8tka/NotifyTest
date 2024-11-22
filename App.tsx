import React, { useState } from "react";
import {
  NativeModules,
  SafeAreaView,
  ScrollView,
  StatusBar,
  Text,
  TouchableOpacity,
  useColorScheme,
  View,
} from 'react-native';

import {Colors, Header} from 'react-native/Libraries/NewAppScreen';
import {
  Notification,
  Notifications,
  Registered,
  RegistrationError,
} from 'react-native-notifications';

const {WorkManagerModule} = NativeModules;

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  Notifications.registerRemoteNotifications();

  Notifications.events().registerRemoteNotificationsRegistered(
    (event: Registered) => {
      console.log('Device Token Received', event.deviceToken);
    },
  );
  Notifications.events().registerRemoteNotificationsRegistrationFailed(
    (event: RegistrationError) => {
      console.error(event);
    },
  );

  Notifications.events().registerNotificationReceivedForeground(
    (notification: Notification, completion) => {
      console.log(
        `Notification received in foreground: ${notification.title} : ${notification.body}`,
      );
      completion({alert: true, sound: true, badge: false});
    },
  );

  Notifications.events().registerNotificationReceivedBackground(
    (notification: Notification, completion) => {
      console.log(
        `Notification received in BACKGROUND: ${notification.title} : ${notification.body}`,
      );
      WorkManagerModule.startWork('Number of start module ' + i);
      completion({alert: true, sound: true, badge: false});
    },
  );

  Notifications.events().registerNotificationOpened(
    (notification: Notification, completion) => {
      console.log(`Notification opened: ${notification.payload}`);
      completion();
    },
  );

  let i = 0;

  const [tapId,setTapId] = useState(i);

  function handlePress() {
    // WorkManagerModule.startWork('Number of start module ' + i)
    //   .then(res => console.log(res))
    //   .catch(e => console.log(e));
    // i++;
    // setTapId(i);
  }

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <TouchableOpacity onPress={handlePress}>
            <View
              style={{
                margin: 10,
                borderRadius: 10,
                padding: 15,
                backgroundColor: '#FF0099',
                alignItems: 'center',
              }}>
              <Text>Нажми на меня</Text>
            </View>
          </TouchableOpacity>
          <View style={{ padding: 15 }}>
            <Text>{`count of tap: ${tapId}`}</Text>
          </View>
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

export default App;
