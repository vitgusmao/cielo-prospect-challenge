import { Descriptions } from 'antd';
import { useNavigate } from 'react-router-dom';

import { NaturalPerson } from '../../../../@types';
import InputMCC from '../../../../components/InputMCC';
import { getApiEndpoint } from '../../../../data';
import Button from '../../../../facades/Button';
import Form from '../../../../facades/Form';
import Input from '../../../../facades/Input';
import Space from '../../../../facades/Space';
import useHttpPut from '../../../../hooks/useHttpPut';

function NaturalPersonForm({ client }: { client: NaturalPerson }) {
  const navigate = useNavigate();
  const put = useHttpPut(getApiEndpoint(`/clients/${client.id}`));

  return (
    <Form
      colon={false}
      layout="vertical"
      initialValues={client}
      onFinish={async (values) => {
        const ok = await put(values, 'cliente editado com sucesso');
        if (ok) {
          navigate('/clients');
        }
      }}
    >
      <Descriptions>
        <Descriptions.Item label="CPF">{client.cpf}</Descriptions.Item>
      </Descriptions>

      <Form.Item
        label="nome"
        name="name"
        rules={[
          {
            required: true,
            message: 'nome não pode conter mais de 50 caracteres!',
            max: 50,
          },
        ]}
        validateFirst
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="e-mail"
        name="email"
        rules={[{ required: true, type: 'email', message: 'e-mail inválido!' }]}
        validateFirst
      >
        <Input />
      </Form.Item>
      <Form.Item
        label="MCC"
        name="mcc"
        tooltip="Merchant Category Code"
        rules={[
          {
            required: true,
            message: 'MMC só pode conter números!',
            transform: (value) => parseInt(value, 10),
            type: 'integer',
          },
          {
            required: true,
            message: 'MMC só pode conter 4 dígitos!',
            max: 4,
          },
        ]}
        validateFirst
      >
        <InputMCC />
      </Form.Item>
      <Space>
        <Form.Item>
          <Button htmlType="submit" type="primary">
            Salvar
          </Button>
        </Form.Item>
        <Form.Item>
          <Button
            danger
            onClick={() => {
              navigate('/clients');
            }}
          >
            Cancelar
          </Button>
        </Form.Item>
      </Space>
    </Form>
  );
}

export default NaturalPersonForm;
