import request from '../connection/http/request';
import useMessage from '../contexts/Message/useMessage';

type PostOptions = { body?: object } & Omit<
  Omit<RequestInit, 'method'>,
  'body'
>;

export default function useHttpPut(url: string, options?: PostOptions) {
  const message = useMessage();

  return async (requestBody?: PostOptions['body'], msg?: string) => {
    return request(url, {
      ...options,
      headers: {
        ...(options?.headers ?? {}),
        'Content-Type': 'application/json',
      },
      body: requestBody,
      method: 'PUT',
    })
      .then((response) => {
        response.json().then((body) => {
          if (response.status !== 200 && 'message' in body) {
            message.error(body.message);
            return false;
          }
          message.success(msg);
          return true;
        });
      })
      .catch((reason) => {
        message.error(reason);
        return false;
      });
  };
}
