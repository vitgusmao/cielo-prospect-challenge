import useMessage from 'antd/es/message/useMessage';
import React from 'react';

import MessageContext from './MessageContext';

function MessageProvider({ children = null }: { children?: React.ReactNode }) {
  const [messageApi, contextHolder] = useMessage();

  return (
    <MessageContext.Provider value={messageApi}>
      {contextHolder}
      {children}
    </MessageContext.Provider>
  );
}

export default MessageProvider;
