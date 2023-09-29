import ConfigProvider from 'antd/es/config-provider';

import colors from './colors';
import MessageProvider from './contexts/Message';
import Routing from './Rounting';

function App() {
  return (
    <ConfigProvider
      theme={{
        token: { colorPrimary: colors.primaryColor },
        components: { Menu: { itemBg: 'transparent' } },
      }}
    >
      <MessageProvider>
        <Routing />
      </MessageProvider>
    </ConfigProvider>
  );
}

export default App;
