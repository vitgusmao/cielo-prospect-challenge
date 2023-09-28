import ConfigProvider from 'antd/es/config-provider';

import colors from './colors';
import Routing from './Rounting';

function App() {
  return (
    <ConfigProvider
      theme={{
        token: { colorPrimary: colors.primaryColor },
        components: { Menu: { itemBg: 'transparent' } },
      }}
    >
      <Routing />
    </ConfigProvider>
  );
}

export default App;
