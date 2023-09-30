/* eslint-disable no-template-curly-in-string */

import { useNavigate } from 'react-router-dom';

import { LegalEntity } from '../../../../@types';
import InputCPF from '../../../../components/InputCPF';
import InputMCC from '../../../../components/InputMCC';
import { getApiEndpoint } from '../../../../data';
import Button from '../../../../facades/Button';
import Descriptions from '../../../../facades/Descriptions';
import Divider from '../../../../facades/Divider';
import Form from '../../../../facades/Form';
import Input from '../../../../facades/Input';
import Space from '../../../../facades/Space';
import useHttpPut from '../../../../hooks/useHttpPut';

import validateCPF from './validateCpf';

function LegalEntityForm({ client }: { client: LegalEntity }) {
  const navigate = useNavigate();
  const put = useHttpPut(getApiEndpoint(`/clients/${client.id}`));

  return (
    <Form
      colon={false}
      layout="vertical"
      onFinish={async (values) => {
        const ok = await put(values, 'cliente editado com sucesso');
        if (ok) {
          navigate('/clients');
        }
      }}
    >
      <Descriptions>
        <Descriptions.Item label="CNPJ">{client.cnpj}</Descriptions.Item>
      </Descriptions>
      <Form.Item
        label="razão social"
        name="corporateReason"
        rules={[
          {
            required: true,
            message: 'razão não pode conter mais de 50 caracteres!',
            max: 50,
          },
        ]}
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
      <Divider>informações do contato do estabelecimento</Divider>
      <Form.Item
        label="CPF"
        name="cpf"
        rules={[
          {
            required: true,
            message: 'CPF só pode conter números!',
            transform: (value) => parseInt(value, 10),
            type: 'integer',
          },
          {
            validator: (_, value) =>
              validateCPF(value)
                ? Promise.resolve()
                : Promise.reject(new Error('CPF inválido!')),
          },
        ]}
        validateFirst
      >
        <InputCPF />
      </Form.Item>
      <Form.Item
        label="nome"
        name="name"
        rules={[
          {
            required: true,
            message: 'nome do contato não pode conter mais de 50 caracteres!',
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
      <Space>
        <Button htmlType="submit" type="primary">
          Salvar
        </Button>
        <Button
          danger
          onClick={() => {
            navigate('/clients');
          }}
        >
          Cancelar
        </Button>
      </Space>
    </Form>
  );
}

export default LegalEntityForm;
