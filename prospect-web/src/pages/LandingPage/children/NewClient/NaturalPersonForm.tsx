import { Link, useNavigate } from 'react-router-dom';

import InputCPF from '../../../../components/InputCPF';
import InputMCC from '../../../../components/InputMCC';
import { getApiEndpoint } from '../../../../data';
import Button from '../../../../facades/Button';
import Form from '../../../../facades/Form';
import Input from '../../../../facades/Input';
import Space from '../../../../facades/Space';
import useHttpPost from '../../../../hooks/useHttpPost';

import validateCPF from './validateCpf';

function NaturalPersonForm() {
  const navigate = useNavigate();
  const post = useHttpPost(getApiEndpoint('/natural-persons'));

  return (
    <Form
      colon={false}
      layout="vertical"
      onFinish={async (values) => {
        const ok = await post(values, 'cliente cadastrado com sucesso');
        if (ok) {
          navigate('/clients');
        }
      }}
    >
      <Form.Item
        label="CPF"
        name="cpf"
        rules={[
          { required: true, message: 'CPF deve ser informado!' },
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
          { required: true, message: 'nome deve ser informado!' },
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
        rules={[
          { required: true, message: 'e-mail deve ser informado!' },
          { required: true, type: 'email', message: 'e-mail inválido!' },
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
          { required: true, message: 'MMC deve ser informado!' },
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
            Cadastrar
          </Button>
        </Form.Item>
        <Form.Item>
          <Button danger>
            <Link to="/clients">Cancelar</Link>
          </Button>
        </Form.Item>
      </Space>
    </Form>
  );
}

export default NaturalPersonForm;
