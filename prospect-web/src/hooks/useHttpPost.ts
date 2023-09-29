import request from '../connection/http/request';
import useMessage from '../contexts/Message/useMessage';

type PostOptions = Omit<RequestInit, 'method'>;

export default function useHttpPost(url: string, options?: PostOptions) {
  const message = useMessage();

  return async (requestBody?: PostOptions['body']) => {
    return request(url, {
      ...options,
      headers: {
        ...(options?.headers ?? {}),
        'Content-Type': 'application/json',
      },
      body: requestBody,
      method: 'POST',
    })
      .then((response) => {
        response.json().then((body) => {
          if (response.status !== 201) {
            message.error(body.message);
          } else if ('message' in body) {
            message.info(body.message);
          }
          return body;
        });
      })
      .catch((reason) => {
        message.error(reason);
      });
  };
}
