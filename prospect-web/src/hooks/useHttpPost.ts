import { useCallback } from 'react';

import request from '../connection/http/request';
import useMessage from '../contexts/Message/useMessage';

type PostOptions = { expectedStatus?: number; body?: object } & Omit<
  Omit<RequestInit, 'method'>,
  'body'
>;

export default function useHttpPost(url: string, options?: PostOptions) {
  const expectedStatus = options?.expectedStatus ?? 201;
  const message = useMessage();

  return useCallback(
    async (requestBody?: PostOptions['body'], msg?: string) => {
      return request(url, {
        ...options,
        headers: {
          ...(options?.headers ?? {}),
          'Content-Type': 'application/json',
        },
        body: requestBody,
        method: 'POST',
      })
        .then(async (response) => {
          try {
            const json = await response.json().then((body) => {
              if (response.status !== expectedStatus && 'message' in body) {
                message.error(body.message);
                return null;
              }
              if (msg) {
                message.success(msg);
              }
              return body;
            });
            return json;
          } catch (e) {
            message.error('Erro inesperado');
            return null;
          }
        })
        .catch((reason) => {
          message.error(reason);
          return null;
        });
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [url]
  );
}
