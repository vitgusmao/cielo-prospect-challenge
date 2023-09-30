import { useEffect, useState } from 'react';

import request from '../connection/http/request';
import useMessage from '../contexts/Message/useMessage';

type GetOptions = Omit<Parameters<typeof request>[1], 'method'>;

export default function useHttpGet<T>(url: string, options?: GetOptions) {
  const [state, setState] = useState<T | undefined>();
  const message = useMessage();

  useEffect(() => {
    request(url, { ...options, method: 'GET' })
      .then((value) => {
        value.json().then((body) => {
          setState(body);
        });
      })
      .catch((reason) => {
        message.error(reason);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return state;
}
