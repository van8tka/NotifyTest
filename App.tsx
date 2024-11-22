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


const {WorkManagerModule} = NativeModules;

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };


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
