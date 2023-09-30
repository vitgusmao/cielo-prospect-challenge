import React from 'react';

import MessageContext from './MessageContext';

export default function useMessage() {
  return React.useContext(MessageContext);
}
