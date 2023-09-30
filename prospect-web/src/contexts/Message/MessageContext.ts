import { MessageInstance, MessageType } from 'antd/es/message/interface';
import React from 'react';

const messages: MessageInstance = {
  info(): MessageType {
    throw new Error('Function not implemented.');
  },
  success(): MessageType {
    throw new Error('Function not implemented.');
  },
  error(): MessageType {
    throw new Error('Function not implemented.');
  },
  warning(): MessageType {
    throw new Error('Function not implemented.');
  },
  loading(): MessageType {
    throw new Error('Function not implemented.');
  },
  open(): MessageType {
    throw new Error('Function not implemented.');
  },
  destroy(): MessageType {
    throw new Error('Function not implemented.');
  },
};

const MessageContext = React.createContext<MessageInstance>(messages);

export default MessageContext;
